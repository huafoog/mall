package com.qingshan.mall.member.service.impl;

import com.qingshan.common.dto.member.MemberDTO;
import com.qingshan.common.dto.member.MemberLoginDTO;
import com.qingshan.common.dto.member.MemberRegisterDTO;
import com.qingshan.mall.member.dao.MemberLevelDao;
import com.qingshan.mall.member.entity.MemberLevelEntity;
import com.qingshan.mall.member.exception.PhoneExistException;
import com.qingshan.mall.member.exception.UserNameExistException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingshan.common.utils.PageUtils;
import com.qingshan.common.utils.Query;

import com.qingshan.mall.member.dao.MemberDao;
import com.qingshan.mall.member.entity.MemberEntity;
import com.qingshan.mall.member.service.MemberService;


@Service
@AllArgsConstructor
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberEntity> page = this.page(
                new Query<MemberEntity>().getPage(params),
                new QueryWrapper<MemberEntity>()
        );

        return new PageUtils(page);
    }

    private final MemberLevelDao memberLevelDao;

    @Override
    public void register(MemberRegisterDTO vo) {
        MemberDao memberDao = this.baseMapper;
        MemberEntity entity = new MemberEntity();
        //设置默认等级
        MemberLevelEntity levelEntity = memberLevelDao.getDefaultLevel();
        if (levelEntity != null){
            entity.setLevelId(levelEntity.getId());
        }


        //检查用户名和手机号是否唯一。为了让controller能感知异常，异常机制
        checkPhoneUnique(vo.getPhone());
        checkUserNameUnique(vo.getUserName());

        entity.setMobile(vo.getPhone());
        entity.setUsername(vo.getUserName());
        //密码加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(vo.getPassword());
        entity.setPassword(encode);
        memberDao.insert(entity);
    }

    @Override
    public void checkPhoneUnique(String phone) throws PhoneExistException {
        MemberDao memberDao = this.baseMapper;
        Integer count = memberDao.selectCount(new QueryWrapper<MemberEntity>().eq("mobile", phone));
        if (count > 0){
            throw new PhoneExistException();
        }
    }

    @Override
    public void checkUserNameUnique(String userName) throws UserNameExistException {
        MemberDao memberDao = this.baseMapper;
        Integer count = memberDao.selectCount(new QueryWrapper<MemberEntity>().eq("username", userName));
        if (count > 0){
            throw new UserNameExistException();
        }
    }



    @Override
    public MemberDTO login(MemberLoginDTO vo) {
        String account = vo.getAccount();
        String password = vo.getPassword();
        //去数据库查询
        MemberDao memberDao = this.baseMapper;
        MemberEntity memberEntity = memberDao.selectOne(new QueryWrapper<MemberEntity>().eq("username", account).or().eq("mobile", account));
        if (memberEntity == null){
            //登录失败
            return null;
        }else {
            //1、获取到数据库的password
            String passwordDB = memberEntity.getPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            //2、密码匹配
            boolean matches = passwordEncoder.matches(password, passwordDB);
            if (matches){
                MemberDTO memberDTO = new MemberDTO();
                BeanUtils.copyProperties(memberEntity,memberDTO);
                return memberDTO;
            }else {
                return null;
            }
        }

    }

}
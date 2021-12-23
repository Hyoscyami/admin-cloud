package ${package.Service}.impl;

import ${package.Entity}.${entity};
import com.xushifei.authorization.server.manager.ClientManager;
import com.xushifei.authorization.server.service.ClientService;
import com.xushifei.authorization.server.vo.ClientVO;
import com.xushifei.common.dto.BaseAddReq;
import com.xushifei.common.dto.BaseUpdateReq;
import com.xushifei.common.service.impl.BaseServiceImpl;
import com.xushifei.common.vo.BaseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ${table.comment!}业务类
 *
 * @author ${author}
 * @since ${date}
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl extends BaseServiceImpl<ClientManager, Client>
    implements ClientService {

  /**
   * 新增请求转实体
   *
   * @param req
   * @return
   */
  @Override
  public Client convertAddReqToEntity(BaseAddReq req) {
    Client client = new Client();
    BeanUtils.copyProperties(req, client);
    return client;
  }

  /**
   * 更新请求转entity
   *
   * @param req
   * @return
   */
  @Override
  protected Client convertUpdateReqToEntity(BaseUpdateReq req) {
    Client client = new Client();
    BeanUtils.copyProperties(req, client);
    return client;
  }

  /**
   * entity转VO
   *
   * @param entity
   * @return
   */
  @Override
  protected BaseVO convertEntityToVO(Client entity) {
    ClientVO vo = new ClientVO();
    BeanUtils.copyProperties(entity, vo);
    return vo;
  }

  /**
   * 入库预处理
   *
   * @param entity
   */
  @Override
  protected void preSave(Client entity) {
    entity.assignCreateInfo();
  }

  /**
   * 删除预处理
   *
   * @param entity
   */
  @Override
  protected void preRemove(Client entity) {
    entity.assignDelInfo();
  }

  /**
   * 更新预处理
   *
   * @param entity
   */
  @Override
  protected void preUpdate(Client entity) {
    entity.assignModifyInfo();
  }
}
